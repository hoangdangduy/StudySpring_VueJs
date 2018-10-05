var app = new Vue({
    el: '#detail-product',
    data: {
        product: {
            id: '',
            lstComment: [],
            nameProduct: '',
            rank: -1
        },

        username: '',
        comment: '',
        rankAverage: 1,
        rankComment: 1,
    },
    watch: {

    },
    methods: {
        getDetailProduct: function() {
            var urlParams = new URLSearchParams(window.location.search);
            axios
                .get('/get-detail-by-id', {
                    params: {
                        id: urlParams.get('id')
                    }
                })
                .then(function(response) {
                    console.log(response);
                    app.product.id = response.data.id;
                    app.product.lstComment = response.data.lstComment;
                    app.product.nameProduct = response.data.nameProduct;
                    app.product.rank = response.data.rank;
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        },

        addComment: function() {
            axios
                .post('/add-comment', {
                    "username": this.username,
                    "comment": this.comment,
                    "rank": this.rankComment,
                    "idProduct": this.product.id
                })
                .then(function(response) {
                    console.log(response.data);
                    axios
                        .get('/get-detail-by-id', {
                            params: {
                                id: this.product.id
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
        }
    }
});

$( document ).ready(function() {
    app.getDetailProduct();
});