
const { default: mongoose } = require("mongoose");
const registerSchema = require("./registerSchema");
const claimSchema = require("./claimSchema");

const DB_NAME = process.env.DB_NAME || "InsuranceClaim App";
const URI = process.env.MONGO_URI || "mongodb://127.0.0.1:27017";
const MONGO_URI = `${URI}/${DB_NAME}`;

mongoose.connect(MONGO_URI).then(() => console.log("Connected")).catch((err) => console.log(err))

const register = mongoose.model('register', registerSchema, 'users');
const claim = mongoose.model('claim', claimSchema, 'todos');

module.exports = {register, claim};
