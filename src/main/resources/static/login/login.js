var app = new Vue({
    el: '#page-login',
    data: {
        username: '',
        password: '',
        mail: '',
        isLoginByUsername: true,
        chooseTypeLogin: ''
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
            // TODO: them function ajax len server
        }
    }
})