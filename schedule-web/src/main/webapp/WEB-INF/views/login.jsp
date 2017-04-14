<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <script type="text/javascript">
        $(function () {
            var error = ${error};
            if (error) {
                $.growl.error({message: "Please log in"});
            } else {
                $.growl({title: "Welcome!", message: "Please log in"});
            }
        });
        $(function () {
            var lock = new Auth0Lock('${clientId}', '${clientDomain}', {
                auth: {
                    redirectUrl: '${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}${loginCallback}',
                    responseType: 'code',
                    params: {
                        state: '${state}',
                        // Learn about scopes: https://auth0.com/docs/scopes
                        scope: 'openid user_id name nickname email picture'
                    }
                }
            });
            // delay to allow welcome message..
            setTimeout(function () {
                lock.show();
            }, 1500);
        });
    </script>
</div>