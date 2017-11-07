$(function() {
    var apiPrefix = getPrefix(getEnv());
    var apiUrl = {
        login: apiPrefix + '/user/login',
        info: apiPrefix + '/user/info'
    };
    window.apiService = {
        get: function(url, data, cb, fcb) {
            this.ajax('GET', url, data, cb, fcb);
        },
        post: function(url, data, cb, fcb) {
            this.ajax('POST', url, data, cb, fcb);
        },
        ajax: function(method, url, data, cb, fcb) {
            console.log(method + ' ' + url + ' - ' + JSON.stringify(data));
            $.ajax({
                url: url,
                method: method,
                data: data,
                type: 'json',
                success: function(res) {
                    console.log(res);
                    typeof cb === 'function' && cb(res);
                    typeof fcb === 'function' && fcb(res);
                },
                error: function(res) {
                    console.log(res);
                    typeof fcb === 'function' && fcb(res);
                }
            })
        },
        login: function(userName, password, callback, fcb) {
            this.get(apiUrl.login, {
                userName: userName,
                password: password
            }, callback, fcb);
        },
        getInfo: function(token, callback, fcb) {
            this.get(apiUrl.info, {token: token}, callback, fcb);
        }
    };

    var ENV_DEV = 1, ENV_PROD = 0;
    function getPrefix(env) {
        switch (env) {
            case ENV_DEV: return '/api';
            case ENV_PROD: return '/api';
        }
    }

    function getEnv() {
        return ENV_DEV;
    }
});

