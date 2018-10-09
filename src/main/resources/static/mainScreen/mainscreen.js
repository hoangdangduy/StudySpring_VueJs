var app = new Vue({
    el: '#main-page-product',
    data: {
        products: [],
        isViewDetail: false,
        product: {},
        rankComment: 0,
        comment: '',
        username: '',
        users: []
    },
    watch: {

    },
    methods: {
        getAllProduct: function() {
            axios.get('/get-all-product')
                .then(function(response) {
                    app.products = response.data
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        },
        viewDetail: function(product) {
            app.product = product;
            app.isViewDetail = true;
            app.getListUser();
        },
        addComment: function() {
            axios
                .post('/add-comment', {
                    "username": app.username,
                    "comment": app.comment,
                    "rank": app.rankComment,
                    "idProduct": "1"
                })
                .then(function(response) {
                    console.log(response.data);
                    axios
                        .get('/get-detail-by-id', {
                            params: {
                                id: app.product.id
                            }
                        })
                        .then(function(response) {
                            console.log(response);
                            app.product.lstComment = response.data.lstComment;
                            app.product.rank = response.data.rank;
                        })
                        .catch(function (error) {
                            console.log(error.response.data);
                        })
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        },
        getListUser: function() {
            axios
                .get('/get-user-by-username',{
                    params: {
                        username: ''
                    }
                })
                .then(function(response) {
                    app.users = response.data;
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        }
    }
});

$( document ).ready(function() {
    app.getAllProduct();
});