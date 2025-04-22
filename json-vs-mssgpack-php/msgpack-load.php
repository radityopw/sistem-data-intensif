#! /bin/php
<?php

require 'shared.php';

$unpacker = new \MessagePackUnpacker(false);

$unpacker->feed(file_get_contents($file_output2));
$unpacker->execute();
$unpacked = $unpacker->data();

echo print_r($unpacked,TRUE);
echo PHP_EOL;

$unpacker->reset();
