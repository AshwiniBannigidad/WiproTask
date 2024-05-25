const mongoose = require('mongoose');

const claimSchema = new mongoose.Schema({
    PolicyholderName: {
        type: String,
        required: true
    },
    Address: {
        type: String,
        required: true,
    },
    ContactInformation: {
        type: String,
        required: true,
    },
    VehicleMake: {
        type: String,
        required: true,
    },
    VehicleYear: {
        type: Boolean,
        required: true,
    },
    LicensePlateNumber: {
        type: String,
        required:true,
        unique: true,
    },
    AccidentDescription: {
        type: Boolean,
        required: true,
    },
});

module.exports = claimSchema;