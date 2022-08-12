<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <?php
    include 'bootstrap.html';
    ?>
</head>
<body>
    <?php
    include 'navigationbar.php';
    ?>
    <div class="container">
    <h3 style="text-align:center;"><b>Update dog detail</b></h3>
    <form action="" method="post">
    <?php
    require('../connection.php');
      if(isset($_GET['id'])){
        $id=$_GET['id'];
	$select="SELECT * from dog_table WHERE id='$id'";
	$run_select=mysqli_query($con,$select);
	while ($row=mysqli_fetch_array($run_select)) {
		$dogId=$row[0];
		$name=$row[1];
		$address=$row[2];
		$image=$row[3];
		$age=$row[4];
		$gender=$row[5];
		$desc=$row[6];
		$breed=$row[7];
	# code...
	}
	echo " <div class='form-row'>
  <div class='form-group col-md-6'>
    <label for='inputName4'>Name</label>
    <input type='name' value='$name' name='name' class='form-control' id='inputName4' placeholder='Name'>
  </div>
  <div class='form-group col-md-6'>
  <label for='inputAddress'>Address</label>
  <input type='text'value='$address' name='address' class='form-control' id='inputAddress' placeholder='Address'>
  </div>
</div>
<div class='form-row'>
  <div class='form-group col-md-6'>
    <label for='inputText4'>Age of dog</label>
    <input type='text' value='$age' name='age' class='form-control' id='inputText4' placeholder='eg: 2 years and 3 moths'>
  </div>
  <div class='form-group col-md-6'>
  <label for='inputText4'>Breed</label>
  <input type='text' value='$breed' name='breed' class='form-control' id='inputBreed' placeholder='breed here'>
  </div>
</div>";
if($gender=="Male"){
  echo " <div class='form-row'>
  <legend class='col-form-label col-sm-2 pt-0'>Gender</legend>
  <div class='col-sm-10'>
    <div class='form-check'>
      <input class='form-check-input' type='radio' value='Male' name='gender' id='gridRadios1' value='option1' checked>
      <label class='form-check-label' for='gridRadios1'>
       Male
      </label>
    </div>
    <div class='form-check'>
      <input class='form-check-input' value='Female' type='radio' name='gender' id='gridRadios2'>
      <label class='form-check-label' for='gridRadios2'>
        Female
      </label>
    </div>
  </div>
</div>";
}else{
  echo " <div class='form-row'>
  <legend class='col-form-label col-sm-2 pt-0'>Gender</legend>
  <div class='col-sm-10'>
    <div class='form-check'>
      <input class='form-check-input' type='radio' value='Male' name='gender' id='gridRadios1'>
      <label class='form-check-label' for='gridRadios1'>
       Male
      </label>
    </div>
    <div class='form-check'>
      <input class='form-check-input' value='Female' type='radio' name='gender' id='gridRadios2' checked>
      <label class='form-check-label' for='gridRadios2'>
        Female
      </label>
    </div>
    
  </div>
</div>";
}

  echo "
  <div class='form-group'>
  <label for='exampleFormControlTextarea1'>Description About Dog</label>
  <textarea name='description' class='form-control' id='exampleFormControlTextarea1' rows='4'>$desc</textarea>
</div>

<button type='button' style='float:right;' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal'>
           Update
         </button>
         <!-- Modal -->
         <div class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>
           <div class='modal-dialog' role='document'>
             <div class='modal-content'>
               <div class='modal-header'>
                 <h5 class='modal-title' id='exampleModalLabel'>Are you sure to update ?</h5>
                 <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                   <span aria-hidden='true'>&times;</span>
                 </button>
               </div>
               <div class='modal-footer'>
                 <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>
                 <button type='submit' name='save' class='btn btn-primary'>Save changes</button>
               </div>
             </div>
           </div>
         </div>";
      }
      else{
        echo "<b style=' color:red;'>No data found</b>";
      }
      if(isset($_POST['save'])){
        if(isset($_POST['name']) && isset($_POST['address']) && isset($_POST['age']) && isset($_POST['breed']) && isset($_POST['gender']) && isset($_POST['description'])){
          $dogName=$_POST['name'];
          $dogAddress=$_POST['address'];
          $dogAge=$_POST['age'];
          $dogBreed=$_POST['breed'];
          $dogGender=$_POST['gender'];
          $dogDesc=$_POST['description'];
          $run=mysqli_query($con,"UPDATE dog_table SET dog_name='$dogName', dog_address='$dogName', dog_age='$dogAge', dog_gender='$dogGender',description='$dogDesc', 
          dog_breed='$dogBreed' WHERE id='$id'");
          if($run){
            echo '<script type="text/javascript">location.href="../home.php"</script>';
          }
          else{
            echo "Updation failed";
          }

  }
 
    else{
      echo " Something went wrong  ";
    }
  }
       
      
    
      mysqli_close($con);

    ?>
</form>
    </div>
    
     <?php
    include 'footer.php';
    ?>
    
</body>
</html>