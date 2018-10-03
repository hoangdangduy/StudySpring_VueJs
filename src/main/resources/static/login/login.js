var app = new Vue({
    el: '#page-login',
    data: {
        username: '',
        password: '',
        mail: '',
        isLoginByUsername: true,
        chooseTypeLogin: 'username'
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
                .post('http://localhost:8080/login-api', {
                    "username": this.username,
                    "mail": this.mail,
                    "password": this.password
                })
                .then(function(response) {
                    alert(response.data);
                    console.log(response.data);
                    axios.get('http://localhost:8080/get-all-login')
                        .then(function (response) {
                            console.log(response);
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