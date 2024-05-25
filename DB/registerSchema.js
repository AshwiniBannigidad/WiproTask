const mongoose = require('mongoose');

const registerSchema = new mongoose.Schema({
    FName: {
        type: String,
        required: true
    },
    LName: {
        type: String,
        required: true,
    },
    Email: {
        type: String,
        required: true,
    },
    pass: {
        type: String,
        required: true,
    },
  
});

module.exports = registerSchema;