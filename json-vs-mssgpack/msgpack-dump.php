#! /bin/php
<?php

require 'shared.php';

$packer = new \MessagePack(false);

$msgpack = $packer->pack($data);
file_put_contents($file_output2,$msgpack);
