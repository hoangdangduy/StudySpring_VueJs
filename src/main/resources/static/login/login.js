var app = new Vue({
    el: '#page-login',
    data: {
        username: '',
        password: '',
        mail: '',
        isLoginByUsername: true,
        chooseTypeLogin: 'username',
        users: []
    },
    watch: {
        chooseTypeLogin: function (newVal, oldVal) {
            if (newVal == "username") {
                this.isLoginByUsername = true;
            } else {
                this.isLoginByUsername = false;
            }
        }
    },
    methods: {
        login: function () {
            axios
                .post('/login-api', {
                    "username": this.username,
                    "mail": this.mail,
                    "password": this.password
                })
                .then(function(response) {
                    console.log(response.data);
                    axios.get('/get-all-login')
                        .then(function (response) {
                            app.users = response.data;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        }
    }
});