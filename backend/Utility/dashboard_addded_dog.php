<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="conatiner">
        <form action="" method="post">
        <?php
	require_once('connection.php');
	$select="SELECT dog_table.*, user_table.* FROM dog_table, user_table WHERE dog_table.user_id=user_table.id";
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
      <button type='button' class='btn btn-primary'><a style='color:white;'href='Utility/editdogform.php?id=$row[0]'>Edit</a></button>
        <button type='button'  class='btn btn-danger'><a type='button' style='color:white;' href='Utility/deletedog.php?id=$row[0]'>Dissmiss</a></button>
        <button type='button' class='btn btn-success' data-toggle='modal' data-target='#exampleModal'>
           Approve
         </button>
         <!-- Modal -->
         <div class='modal fade' id='exampleModal' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>
           <div class='modal-dialog' role='document'>
             <div class='modal-content'>
               <div class='modal-header'>
                 <h5 class='modal-title' id='exampleModalLabel'>Are you sure to approve?</h5>
                 <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                   <span aria-hidden='true'>&times;</span>
                 </button>
               </div>
               <div class='modal-footer'>
                 <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>
                 <button type='submit' name='approve' class='btn btn-primary' data-toggle='modal' data-target='#exampleModalLong'><a type='button' style='color:white;'href='Utility/approved_dogs.php?id=$id'>Approve</a></button>
                 <!-- Modal -->
              <div class='modal fade' id='exampleModalLong' tabindex='-1' role='dialog' aria-labelledby='exampleModalLongTitle' aria-hidden='true'>
                <div class='modal-dialog' role='document'>
                  <div class='modal-content'>
                    <div class='modal-header'>
                      <h5 class='modal-title' id='exampleModalLongTitle'>Dog Approved</h5>
                      <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                        <span aria-hidden='true'>&times;</span>
                      </button>
                    </div>
                    <div class='modal-footer'>
                      <button type='button' class='btn btn-primary'>Okay</button>
                    </div>
                  </div>
                </div>
              </div>
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
    <script>

</script>
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