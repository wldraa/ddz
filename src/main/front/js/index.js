
$(function() {
var vm = new Vue({
    el: '#app',
    _this: null,
    user: {

    },
    data: {
        stage: 'loading',
        loginErrorMessage: "",
        loginUserName: "",
        loginPassword: "",
        tables: []
    },
    computed: {

    },
    mounted: function() {
        _this = this;
        var token = localStorage.token;
        if (!token) {
            _this.stage = 'login';
        } else {
            apiService.getInfo(token, function(res) {
                if (res.success) {
                    _this.user = res.data;
                    _this.stage = 'game';
                } else {
                    _this.stage = 'login';
                }
            });
        }
    },
    methods: {
        login: function() {
            this.loginErrorMessage = "";
            $('#login-submit').button('loading');
            apiService.login(this.loginUserName, this.loginPassword, function(res) {
                if (res.success) {
                    localStorage.token = res.data;
                } else {
                    _this.loginErrorMessage = res.message;
                }
            }, function () {
                $('#login-submit').button('reset');
            });
        }
    }

});
});
