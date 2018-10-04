var app = new Vue({
    el: '#main-page-product',
    data: {
        products: [],
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
        }
    }
});

$( document ).ready(function() {
    app.getAllProduct();
});