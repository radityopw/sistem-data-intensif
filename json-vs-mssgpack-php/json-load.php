#! /bin/php
<?php

require 'shared.php';

$json = json_decode(file_get_contents($file_output),TRUE);

echo print_r($json,TRUE);
echo PHP_EOL;
