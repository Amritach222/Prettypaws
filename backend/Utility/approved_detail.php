<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['password']))
{

}
else{
    header("location:https://localhost/dogFinderApp");
}
?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approved Dogs</title>
    <?php
    include('bootstrap.html');
    ?>
</head>
<body>
    <?php
    include('navigationbar.php');
    ?>
    
    <div class="container">
        <div style="text-align:center;">
        <h1 style=" font-family:TimesNewRoman;">Approved Dogs</h1>
        </div>
    <div class="col" id="right">
    <form action="" method="post">
        <?php
	require_once('../connection.php');
	$select="SELECT approved_dogs.*, user_table.* FROM approved_dogs, user_table WHERE approved_dogs.user_id=user_table.id";
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
        echo "<div class='card'>
        <div class='card-body'>
        <div class='row_card ' style='display:inline-block;'>
        <div class='col_card'>
        <img class='dog_image' src='https://localhost/dogFinderApp/dogImages/$row[3]'/>
        </div>
        <div class='col_card1'>
          <div name='dogname'>
          <b>$row[1]</b>
          </div>
          <br>
          <div name='address'>
          Location of dog: 
          $row[2]
          </div>
          <div name='age'>
          Age: 
          $row[4]
          </div>
          <div name='gender'>Gender: 
          $row[5]
          </div>
          <div name='breed'>Breed: 
          $row[7]
          </div>
          <div name='addedUserName'>Name of added user:
          $row[10]
          </div>
          <div name='userEmail'>Email of added user: 
          $row[11]
          </div>
          <div name='disc'>Description of dog: 
          $row[6]
          </div>
        </div>
      </div>
      <div class='card_buttons'>
        <button type='button'  class='btn btn-danger' data-toggle='modal' data-target='#exampleModal'>Delete</button>
        <!-- Modal -->
                <div class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>
                <div class='modal-dialog' role='document'>
                    <div class='modal-content'>
                    <div class='modal-header'>
                        <h5 class='modal-title' id='exampleModalLabel'>Are you sure to delete?</h5>
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                        <span aria-hidden='true'>&times;</span>
                        </button>
                    </div>
                    <div class='modal-footer'>
                        <button type='button' class='btn btn-secondary' data-dismiss='modal'>No</button>
                        <button type='button' class='btn btn-primary'><a href='deleteapprovedog.php?id=$id' style='color:white;'>Yes</a></button>
                    </div>
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
</form>

    </div>
    </div>

    <?php
    include('footer.php');

    ?>
      <style>
        .card{
      border: 1px solid #ccc;
      background-color: #e9e8f4;
      margin-bottom: 10px;
      border-radius: 10px;
    }
    .row_card{
        
    }

        .col_card{
            display:inline-block;
            
        }
        .col_card1{
            display:inline-block;

        }
        .dog_image{
            height:auto;
            width:250pt;
            padding:10pt;
            border-radius: 25px;
        }
        div.card_buttons{
            text-align:right;
            margin:10px;
        }
    </style>
</body>
</html>