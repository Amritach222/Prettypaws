<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adopting dogs</title>
    <?php
    include('bootstrap.html');
    ?>
</head>
<body>
    <?php
        include('navigationbar.php');
    ?>
    <div class="container-fluid">
        <?php
            require_once('../connection.php');
            $select="SELECT approved_dogs.*, adoption.*, user_table.*FROM approved_dogs, adoption, user_table WHERE adoption.user_id=user_table.id and adoption.dog_id=approved_dogs.id";
            $result['dog']=array();
            $run_select=mysqli_query($con,$select);
            while ($row=mysqli_fetch_array($run_select)) {
                $id=$row[0];
                $dogname=$row[1];
                $doglocation=$row[2];
                $imageStore=$row[3];
                $dogage=$row[4];
                $doggender=$row[5];
                $dogmessage=$row[6];
                $dogbreed=$row[7];
                $userid=$row[8];
                $ques1=$row[10];
                $ques2=$row[11];
                $ques3=$row[12];
                $ques4=$row[13];
                $ques5=$row[14];
                $ques6=$row[15];
                $ques7=$row[16];
                $ques8=$row[17];
                $ques9=$row[18];
                $ques10=$row[19];
                echo "<div class='card'>
                <div class='card-body'>
                <div class='row'>
                <div class='col-sm-3'>
                <img style='height:auto; width:100%;'class='dog_image' src='https://localhost/dogFinderApp/dogImages/$row[3]'/>
                </div>
                <div class='col-sm-4'>
                  <div name='dogname'>
                  <b>$row[1]</b>
                  </div>
                  <br>
                  <div name='address'>
                  Location of dog: 
                  $row[2]
                  </div>
                  <div name='age'>
                  Adopting Person Name: 
                  $row[22]
                  </div>
                  <div name='gender'>
                  Email of Adopting Person: $row[23]
                  </div>
                  <div name='disc'>Description of dog: 
                  $row[6]
                  </div>
                </div>
                <div class='col-sm-5'>
                <h3 style='text-align:center'>Questions:-</h3>
                <div class='question'>
                <p>Have you had a pet before ?</p>
                <p>Ans: $ques1</p>
                </div>
                <div class='question'>
                <p>Do you have children ?</p>
                <p>Ans: $ques2</p>
                </div>
                <div class='question'>
                <p>Does everybody in your household agree with this adoption ?</p>
                <p>Ans: $ques3</p>
                </div>
                <div class='question'>
                <p>Do you own your home ?</p>
                <p>Ans: $ques4</p>
                </div>
                <div class='question'>
                <p>Are you married ?</p>
                <p>Ans: $ques5</p>
                </div>
                <div class='question'>
                <p>Do you currently have any other pets ?</p>
                <p>Ans: $ques6</p>
                </div>
                <div class='question'>
                <p>Do you currently have any other pets ?</p>
                <p>Ans: $ques7</p>
                </div>
                <div class='question'>
                <p>Do you have a preferred age for the dog ?</p>
                <p>Ans: $ques8</p>
                </div>
                <div class='question'>
                <p>Will you stay up to date with vaccines ?</p>
                <p>Ans: $ques9</p>
                </div>
                <div class='question'>
                <p>Do you know a veterinarian you can go to ?</p>
                <p>Ans: $ques10</p>
                </div>
                </div>
              </div>
              <div class='card_buttons' style='text-align:right;'>
              <button type='button'  class='btn btn-danger' ><a style='color:white;'href='deleteadoptdog.php?d_id=$row[20]&u_id=$row[9]'>Not Qualified</a></button>
              <button type='button'  class='btn btn-primary' data-toggle='modal' data-target='#exampleModal'>Contact Adopting Person</button>
                <!-- Modal -->
                <div class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>
                <div class='modal-dialog' role='document'>
                    <div class='modal-content'>
                    <div class='modal-header'>
                        <h5 class='modal-title' id='exampleModalLabel'>Contact for adotion</h5>
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                        <span aria-hidden='true'>&times;</span>
                        </button>
                    </div>
                    <div class='modal-body'>
                    <form action='' method='post'>
                    <div class='form-group'>
                    <label for='exampleFormControlInput1'>Email</label>
                    <input value='$row[23]' type='email' name='email' class='form-control' id='exampleFormControlInput1' placeholder='name@example.com'>
                </div>
                <div class='form-group'>
            <label for='exampleFormControlTextarea1'>Message for adoting person</label>
            <textarea name='message'class='form-control' id='exampleFormControlTextarea1' rows='3'></textarea>
            </div>
                    
                </div>
                    <div class='modal-footer'>
                        <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cancel</button>
                        <button type='submit' name='send'  class='btn btn-primary'>Send Mail</button>
                    </div>
                    </form>
                    </div>
                </div>
                </div>
            
                </div>
                </div>
                </div>";
                
        
        
        
            # code...
            }
            mysqli_close($con);
        ?>
         </div>
    </div>
    <?php
        if(isset($_POST['send'])){
            $email=$_POST['email'];
            $message=$_POST['message'];
            require_once('../PHPMailer/class.smtp.php');

                require_once('../PHPMailer/class.phpmailer.php');

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
                $mail->Subject = 'Dog Adoption';
                $mail->Body =$message;
                if ($mail->send())
                {
                    echo "<br><div class='alert alert-success text-center'>
                    <strong>email Successfully sent</strong> 
                    </div>
                 ";
                    
                }
                else{
                    echo "<br><div class='alert alert-danger text-center'>
                    <strong>Connection Error..</strong> 
                </div> ";
                }
        }
    ?>
    <?php
        include('footer.php');
    ?>
    <style>
        div.question{
           
            line-height:0.1;
        }
        a{
            text-decoration: none;
        }
    </style>
</body>
</html>