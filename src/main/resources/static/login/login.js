var app = new Vue({
    el: '#page-login',
    data: {
        username: '',
        password: '',
        mail: '',
        isLoginByUsername: true,
        chooseTypeLogin: 'username',
        users: [],
        isLogin: false
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
        login: function() {
            axios
                .post('/login-api', {
                    "username": this.username,
                    "mail": this.mail,
                    "password": this.password
                })
                .then(function(response) {
                    console.log(response.data);
                    if (checkExistUser(response.data.password)) {
                        app.isLogin = true;
                    }
                    axios.get('/get-all-login')
                        .then(function (response) {
                            app.users = response.data
                           /* Vue.nextTick(function () {
                                app.refresh();
                            })*/
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                })
                .catch(function (error) {
                    console.log(error.response.data);
                })
        },
        addUser: function() {
            axios
                .post('/add-user', {
                    "username": this.username,
                    "mail": this.mail,
                    "password": this.password
                })
                .then(function(response) {
                    console.log(response.data);
                    if (checkExistUser(response.data.password)) {
                        app.isLogin = true;
                    }
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
                    alert("Trung username hoac email");
                })
        },

        deleteUser: function(id) {
            axios
                .get('/delete-by-id', {
                    params: {
                        id: id
                    }
                })
                .then(function(response) {
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
                    alert("Xoa bi loi");
                })
        },

        editUser: function(event, id) {
            if ($(event.target).text() == "Edit") {
                $(event.target).html("Update");
                $( event.target ).closest( "tr" ).find("td input").each(function(index) {
                    if ($(this).is('[readonly]')) {
                        $(this).removeClass("no-border");
                        $(this).removeAttr("readonly");
                    }
                })
            } else {
                $(event.target).html("Edit");
                var username = $( event.target ).closest( "tr" ).find("input[name='username']")[0].value;
                var mail = $( event.target ).closest( "tr" ).find("input[name='mail']")[0].value;
                var password = $( event.target ).closest( "tr" ).find("input[name='password']")[0].value;
                axios
                    .post('/add-user', {
                        "username": username,
                        "mail": mail,
                        "password": password,
                        "id": id
                    })
                    .then(function(response) {
                        console.log(response.data);
                        if (checkExistUser(response.data.password)) {
                            app.isLogin = true;
                        }
                    })
                    .catch(function (error) {
                        console.log(error.response.data);
                        alert("Trung username hoac email");
                    })
                    .then(function() {
                        axios.get('/get-all-login')
                            .then(function (response) {
                                app.users = response.data;
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    })

                $( event.target ).closest( "tr" ).find("td input").each(function(index) {
                    $(this).addClass("no-border");
                    $(this).attr("readonly","readonly");
                })
            }

        }

        /*refresh: function() {
            $('table.table tbody').find('tr').each(function () {
                new app.rowEditor($(this));
            });
        },

        rowEditor: function(tr) {
            var txtUserName = tr.find("input[name='username']");
            var txtEmail = tr.find("input[name='mail']");
            var btnEdit = tr.find("button.btn-edit");

            var editing = false;

            btnEdit.on("click", function() {
                if (editing) {
                    btnEdit.text("Edit");

                    txtUserName.attr('readonly', 'readonly');

                    // call save service
                } else {
                    btnEdit.text("Update");

                    txtUserName.removeAttr('readonly');
                }

                editing = !editing;
            });
        }*/
    }
});

function checkExistUser(password) {
    return !_.isEmpty(password);
}
