
$(function() {
var vm = new Vue({
    el: '#app',
    _this: null,
    statusTimer: null,
    user: {

    },
    data: {
        stage: 'loading',
        errorMessage: '',
        loginErrorMessage: "",
        loginUserName: "",
        loginPassword: "",
        tables: [],
        status: {}
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
                    _this.toTable();
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
                    _this.toTable();
                } else {
                    _this.loginErrorMessage = res.message;
                }
            }, function () {
                $('#login-submit').button('reset');
            });
        },
        toTable: function() {
            _this.stage = 'room';
            apiService.getTableList(function(res) {
                if (res.success) {
                    _this.tables = res.data;
                    setTimeout(function() {
                        $('.room-tables').ace_scroll({size: 576});
                    }, 1000);
                } else {
                    _this.errorMessage = res.message;
                }
            });
        },
        joinTable: function(e) {
            var targetElement = $(e.target);
            var seatName = targetElement.data('name');
            var tableId = targetElement.parents('.tables').data('name');
            console.log(seatName, tableId);
            targetElement.button('loading');
            apiService.joinTable(tableId, seatName, function(res) {
                if (res.success) {
                    _this.toGame();
                }
            }, function () {
                targetElement.button('reset');
            })
        },
        toGame: function() {
            _this.stage = 'game';
            _this.registerStatusReciever();
        },
        registerStatusReciever: function() {
            if (!_this.statusTimer) {
                var retry = 0;
                _this.statusTimer = setInterval(function() {
                    apiService.getStatus(function(res) {
                        if (res.success) {
                            _this.status = res.data;
                        } else {
                            retry++;
                            if (retry > 2) {
                                _this.errorMessage = res.message;
                            }
                        }
                    }, function() {
                        retry++;
                        if (retry > 2) {
                            _this.errorMessage = '服务器错误';
                        }
                    });
                }, 1000);
            }
        }
    }

});
});
