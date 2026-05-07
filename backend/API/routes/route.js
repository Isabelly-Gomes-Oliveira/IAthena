const express = require("express");

const textoController = require("../controllers/DataController");

const router = express.Router();

router.post("/teste", textoController.enviarTexto);

module.exports = router;