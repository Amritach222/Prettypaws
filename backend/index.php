<?php
    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<body>
    <div class="container-fluid">
    <?php
    
    if (isset($_POST['send']))
    {
        $host='localhost';
    $name='root';
    $pass='';
    $dbname='prettypaws';
     $connect=mysqli_connect($host,$name,$pass,$dbname);
        $email=$_POST['emailid'];
        $v_code=rand(10000,99999);
        $validmail="select * from login where admin_email='$email'";
        $runquery=mysqli_query($connect,$validmail);
        $no_of_rows=mysqli_num_rows($runquery);
        if($no_of_rows>0)
        {
require_once('PHPMailer/class.smtp.php');

require_once('PHPMailer/class.phpmailer.php');

$mail= new PHPmailer();
$mail->isSMTP();
$mail->Host = 'smtp.gmail.com';
$mail->SMTPAuth= true;
//$mail->isHTML(true);
$mail->Username='motherland17102@gmail.com';
$mail->Password='9806138065';
$mail->Port ='465';
$mail->SMTPSecure ='ssl';
//Email Setting
$mail->isHTML(true);
//$mail->setForm('amritach@gmail.com','Amrit Acharya');
//$mail->Subject = 'Hello world';
//$mail->Body ='Test Email';
$mail->AddAddress($email);
//$mail->addReplyTo('amritac@gmail.com','Amrit Acharya');
$mail->Subject = 'Verification';
$mail->Body ="<h2 > This is your Verification code:</h2> <h1>$v_code</h1>";
if ($mail->send())
{
    $_SESSION['admin_email']=$email;
    mysqli_query($connect,"update login set code='$v_code' where id='1'");
    header("location:forgetpassword.php");
}
else{
    echo "<br><div class='alert alert-danger text-center'>
    <strong>Connection Error..</strong> 
  </div> ";
}
        }
    else{
        echo "<br><div class='alert alert-danger text-center'>
        <strong>Incorrect Email. </strong> 
      </div>";
    }
    mysqli_close($connect);
    }
   
  ?>
        
        <div class="row">
        <div class="col-4" align="center">
                <div class="heading_letter">
                    <p><h1>SIGN <span style="color:green">IN</span> </h1></p>
                </div>
                
                <br>
                <div class="top-1">
                    <form class="" action="" method="POST">
                    <div class="form" >
                        <input type="text"  name ="username"required autocomplete="off"  >
                        <label for="name" class="label-name">
                        <span class="content-name">Username</span>
                        </label>
                        
                        </div>
                        <br>
                        <br>
                        <div class="form" >
                        
                        <input type="password"  name ="password" required autocomplete="off" >
                        <label for="name" class="label-name">
                        <span class="content-name">Password</span>
                        </label>
                        
                        </div>
                                <br>
                                <br>
                                
                        <div class="checkbox">
                                <input class="cbox" type="checkbox" name="checkbox" required /> 
                                Accept The <span style="color:green"><a href="#">Terms and Conditions</a></span>
                            </div>
                                
                        <div class="rows">
                            <input class="input" type="submit" value="SIGN IN" style="font-weight:bold" name="submit">
                        </div>
                        </form>
                    
                    
                        <?php
                        $host='localhost';
                            $name='root';
                            $pass='';
                            $dbname='prettypaws';

                            $connect=mysqli_connect($host,$name,$pass,$dbname);
                            if(isset($_POST['submit']))
                            {
                                @$username=mysqli_real_escape_string($connect, $_POST['username']);//mysqli_real_escape_string() is use to protect from sql injections
                                @$password=md5(mysqli_real_escape_string($connect,$_POST['password']));
                                $query="select * from login where username='$username' and password='$password'";
                                $check=mysqli_query($connect,$query);
                                $count=mysqli_num_rows($check);
                                if($count>0)
                                {
                                    $_SESSION['username']=$username;
                                    $_SESSION['password']=$password;
                                    header("location:home.php");
                                }
                            else
                            {
                                
                                echo "<p class='text' >Username/Password does not exist!</p>";
                            }
                            }
                        ?>
                    <div class="footer">
                <div class="container">
  
  <!-- Button to Open the Modal -->
  <br>
  
  <a type="" href="" class="text-white link" data-toggle="modal" data-target="#myModal">
    Forget Password?
  </a>
  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        <form action="index.php" method="post">
  <div class="form-group">
    <label class="text-left" for="email">Enter your email address we will send you a verification code to change password.</label>
    <input type="email" name="emailid" class="form-control" placeholder="Enter email" id="email">
    <br>
    <button type="submit" name="send"  class="btn btn-primary ">Submit</button>
  </div>
  </form>
  <style>
  .text-left{
      float:left;
  }
  .btn{
      float:right;
  }
  </style>
          </div>
        
      </div>
    </div>
  </div>
  
</div>

                        </p>
                    </div>

                </div>

            
        </div>
        <div class="col-7" align="center"> <br>
        <br>
        <p class="contents"><span class="company_name"><i>Pretty<span style="color:green">Paws</span></i></span> <br><br>
        <span><img src="https://img.icons8.com/office/40/000000/marker.png"> <i>Newroad, Pokhara</i></span><br>
        <span><img src="https://img.icons8.com/nolan/44/phone.png"> <i>+977-9814119703, +977-9843886343</i></span> <br>
        <span> <img src="https://img.icons8.com/plasticine/44/000000/gmail.png"><i>prettypawsinfo@gmail.com</i></span>
        </p>
        <p class="companny_discription">
        
        </p>
        </div>
        
    </div>
    </div>
    

</body>
<style type="text/css">
   
    
    input[type=submit] {
        padding: 12px;
        border-radius: 10px;
        box-sizing: border-box;
        border: 2px none black;
        resize: vertical;
        outline:none;
    }
    .form{
        position:relative;
        
        height:50px;
    }
    .form input{
        height:100%;
        width:100%;
        padding-top:20px;
        font-size:16px;
        border:hidden;
        background:transparent;
        outline:none;
        color:white;
    }
    .text{
        text-shadow: 3px 3px black;
        -webkit-filter: blur(0.5px);
        font-weight:bold;
    }
    .form label{
        position:absolute;
        bottom:0px;
        left:0%;
        width:100%;
        height:100%;
        pointer-events:none;
        border-bottom: 1px solid white;
        
        color:white;
        
    }
    .form label::after{
        content:"";
        position:absolute;
        left:0px;
        bottom:-1px;
        height:100%;
        width:100%;
        

    }
    .content-name{
        position:absolute;
        bottom:5px;
        left:0px;
        transition: all 1s ease;
    }
    .form input:focus +.label-name .content-name ,
    .form input:valid +.label-name .content-name {
        transform: translateY(-150%);
        font-size:14px;
        color:lightblue;
    }
    .form input:focus +.label-name ::after, .form input:valid +.label-name ::after{
        transform: translateX(0%);
    }
    .contents{
        font-family:TimesNewRoman;
        color:white;
        font-weight:bold;
        font-size:20px;
    }
    .company_name i{
        font-size:70px;
        padding-top:100px;
        
    }
    
    input[type=submit] {
        margin: 10px auto;
        font-family:TimesNewRoman;
        background-color:rgb(36, 173, 36);
        color:white;
        width:300px;
        border-radius:30px;
    }
    p.text
    {
        color:red;
    }
    
    .head {
        text-align: center;
    }
    .admin_image{
        background:url('admin.jpg');
        height:100px;
        width:100px;
        background-size: cover;
        background-position: center;
        float:left;
        margin-left:100px;
        border-radius:50px;
    }
    body {
        color:black;
        background:linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('images/adoptionImage.jpg');
        background-size: cover;
        background-position: center;
        width: 100%;
        height: 100%;
        padding-left:15px;
        display:flex;
    }
    .heading_letter{
        color: white;
        font-size: 30px;
        font-family:;
        font-weight:bold;
        padding-top:30px;
        
    }
    .col-4{
        background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('images/cloud.jpg');
  background-position: center top;
        width:100%;
        height:595px;
        background-size: cover;
        background-position: center;
        margin-top:15px;
        border-style:hidden;
        border-radius:10px;
        box-shadow: 10px 15px 5px 5px 5px rgba(42, 41, 41, 0.2);
        border-collapse: collapse;
    }
    
    .link{
        color:blue;
        font-family:TimesNewRoman;
        float:right;
    }

    a.link:hover {
        color: blue;
        font-size: 15px;
        
    }
    .checkbox{
        display:inline;
        color:white;
        font-size:15px;
       
    }
    .cbox{
            background:transparent;
            height:15px;
            width:15px;
            border: 1px solid white;
    }

        
    }
    .hrline{
        border-bottom:3px solid green;
    }

    
    input[type=submit]:hover {
        background-color: green;
        font-weight: bold;
    }
    
    @media screen and(max-width: 600px) {
        .column1,
        .column2,
        input[type=submit] {
            width: 300px;
            margin-top: 0px;
        }
        body {
            width: 100%;
        }
        .top {
            width: 300px;
        }
    }
</style>
</body>

</html>
