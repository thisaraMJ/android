<?php

class dbConfig
{
    public $servername;
    public $username;
    public $password;
    public $databasename;

    public function __construct()
    {

        $this->servername = 'localhost';
        $this->username = 'root';
        $this->password = '';
        $this->databasename = 'project';

    }
}

?>
