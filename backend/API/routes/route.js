const express = require("express");

const textoController = require("../controllers/DataController");

const router = express.Router();

router.post("/teste", (req, res) => textoController.enviarTextoParaApp(req, res));

module.exports = router;