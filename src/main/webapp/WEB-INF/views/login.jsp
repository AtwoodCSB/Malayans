<jsp:include page="header.jsp"></jsp:include>
    <body id="login">
		<div id="login-wrapper" class="png_bg">
		    <div id="login-top">
		        <h1>Malayans</h1>
		        <a href="https://github.com/MikeCoder/Malayans">
		        <img id="logo" src="resources/images/logo.png" alt="Project Logo" /></a> 
		    </div>
		    <div id="login-content">
		        <form action="logincheck" Method="POST">
		            <div class="notification information png_bg">
		                <div>
		                    Just click "Sign In". No password needed. 
    		            </div>
    	   	        </div>
	       	        <p>
		                <label>Username</label>
		                <input class="text-input" type="text" name="Username"/>
		            </p>
		            <div class="clear"></div>
			        <p>
			            <label>Password</label>
			            <input class="text-input" type="password" name="Password"/>
			        </p>
			        <div class="clear"></div>
			        <p id="remember-password">
			            <input type="checkbox" />
			            Remember me </p>
			           <div class="clear"></div>
			        <p>
			            <input class="button" type="submit" value="Sign In" />
			        </p>
			    </form>
		    </div>
		</div>
	</body>
</html>