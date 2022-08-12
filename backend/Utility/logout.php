<?php
    session_start();
    unset($_SESSION['username']);
    unset($_SESSION['password']);
    header("location:https://localhost/dogFinderApp");
?>