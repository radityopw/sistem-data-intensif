#! /bin/php
<?php

require 'shared.php';

$json = json_encode($data);
file_put_contents($file_output,$json);
