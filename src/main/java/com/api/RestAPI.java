package com.api;

import com.dom.Comment;
import com.dom.Product;
import com.dom.User;
import com.repo.CommentRepository;
import com.repo.ProductRepository;
import com.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestAPI {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    /*
     *  Check login
     */
    @RequestMapping(value = "/login-api", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) {

        User userFind = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userFind != null) {
            return new ResponseEntity<User>(userFind, HttpStatus.OK);
        }

        userFind = repository.findByMailAndPassword(user.getMail(), user.getPassword());
        if (userFind != null) {
            return new ResponseEntity<User>(userFind, HttpStatus.OK);
        }

        return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }

    /*
     *  Add user
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userFind = repository.findByUsernameOrMail(user.getUsername(), user.getMail());
        if (userFind == null) {
            repository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }

    /*
     *  Get all user
     */
    @RequestMapping(value = "/get-all-login", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> lstUser = new ArrayList<>();
        repository.findAll().forEach(domain -> lstUser.add(domain));
        return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
    }

    /*
     *  Delete user
     */
    @GetMapping("/delete-by-id")
    public String greeting(@RequestParam(name="id") long id) {
        repository.deleteById(id);
        return "greeting";
    }

    @RequestMapping(value = "/get-all-product", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProduct() {
        Pageable pageable = PageRequest.of(1, 20);

        List<Product> lstProduct = new ArrayList<>();
        productRepository.findAll().forEach(domain -> {
            lstProduct.add(domain);
        });
        return new ResponseEntity<List<Product>>(lstProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-detail-by-id", method = RequestMethod.GET)
    public ResponseEntity<Product> getDetailProduct(@RequestParam(name="id") long id) {
        return new ResponseEntity<Product>(productRepository.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add-comment", method = RequestMethod.POST)
    public ResponseEntity<String> addComment(@RequestBody Map<String, String> mapData) {
        String username = mapData.get("username");
        String commentText = mapData.get("comment");
        Integer rank = Integer.parseInt(mapData.get("rank"));
        Long idProduct = Long.parseLong(mapData.get("idProduct"));
        Comment comment;
        float rankAverage;

        Product product = productRepository.findById(idProduct).get();
        Optional<Comment> optComment = commentRepository.findByUsernameAndProduct(username, product);
        if (optComment.isPresent()) {
            comment = optComment.get();
            comment.setComment(commentText);
            comment.setRank(rank);
            commentRepository.save(comment);
        } else {
            Comment commentNew = new Comment(username, commentText, rank, product);
            commentRepository.save(commentNew);
        }

        List<Comment> lstComment = commentRepository.findByProduct(product);
        Double rankAverageD = lstComment.stream().mapToDouble(dom -> dom.getRank()).average().getAsDouble();
        rankAverage = rankAverageD.floatValue();
        product.setRank(rankAverage);
        productRepository.save(product);
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }

    @RequestMapping(value = "/get-user-by-username", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUserByUsername(@RequestParam(name="username") String username) {

        return new ResponseEntity<List<User>>(repository.findByUsernameContaining(username), HttpStatus.OK);
    }
}
