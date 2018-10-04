var app = new Vue({
    el: '#detail-product',
    data: {
        product: {}
    },
    watch: {

    },
    methods: {
        getDetailProduct: function() {
            axios
                .get('/get-detail-by-id', {
                    params: {
                        id: id
                    }
                })
                .then(function(response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        },

    }
});

$( document ).ready(function() {
    app.getDetailProduct();
});