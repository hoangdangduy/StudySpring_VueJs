var app = new Vue({
    el: '#main-page-product',
    data: {
        products: [],
        isViewDetail: false,
        product: {},
        rankComment: 0,
        comment: '',
        username: '',
        comment: '',
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
        }
    }
});

$( document ).ready(function() {
    app.getAllProduct();
});