// server.js
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

mongoose.connect('mongodb+srv://test:test@cluster0.eiffbvz.mongodb.net/', { useNewUrlParser: true, useUnifiedTopology: true });

const VehicleSchema = new mongoose.Schema({
  make: String,
  model: String,
  year: Number,
  vehicleNumber: String,
  chassisNumber: String,
  engineStatus: String,
  fuelLevel: String,
  vehicleCondition: String,
  insuranceProvider: String,
  insurancePolicyNumber: String,
  insuranceStartDate: String,
  insuranceEndDate: String,
  phoneNumber: String // To be used for login
});

const Vehicle = mongoose.model('Vehicle', VehicleSchema);

app.post('/register', async (req, res) => {
  const vehicle = new Vehicle(req.body);
  try {
    await vehicle.save();
    res.status(201).send(vehicle);
  } catch (error) {
    res.status(400).send(error);
  }
});

app.post('/login', async (req, res) => {
  const { phoneNumber } = req.body;
  try {
    const vehicle = await Vehicle.findOne({ phoneNumber });
    if (!vehicle) {
      return res.status(404).send({ error: 'Vehicle not found' });
    }
    res.send(vehicle);
  } catch (error) {
    res.status(400).send(error);
  }
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
