const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const app = express();
const PORT = process.env.PORT || 3000;

// MongoDB connection
mongoose.connect('mongodb://localhost:27017/insurance_app', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// User schema
const registerSchema = new mongoose.Schema({
  username: String,
  password: String,
});

// Claim schema
const claimSchema = new mongoose.Schema({
  userId: mongoose.Schema.Types.ObjectId,
  policyholderName: String,
  address: String,
  contact: String,
  vehicleMake: String,
  vehicleModel: String,
  vehicleYear: Number,
  licensePlate: String,
  accidentDescription: String,
});

// User model
const User = mongoose.model('User', userSchema);

// Claim model
const Claim = mongoose.model('Claim', claimSchema);

// Middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Registration endpoint
app.post('/register', async (req, res) => {
  try {
    const { username, email, password } = req.body;
    const newUser = await User.create({ username, email, password });
    res.status(201).json({ message: 'User registered successfully', data: newUser });
  } catch (error) {
    res.status(500).json({ message: 'Error registering user', error: error.message });
  }
});

// Login endpoint
app.post('/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    const user = await User.findOne({ email, password });
    if (user) {
      res.status(200).json({ message: 'Login successful', data: user });
    } else {
      res.status(401).json({ message: 'Invalid email or password' });
    }
  } catch (error) {
    res.status(500).json({ message: 'Error logging in', error: error.message });
  }
});

// Claim insurance endpoint
app.post('/claim', async (req, res) => {
  try {
    // Assuming the user is authenticated and userId is available in the request
    const { userId, policyholderName, address, contact, vehicleMake, vehicleModel, vehicleYear, licensePlate, accidentDescription } = req.body;
    const newClaim = await Claim.create({ userId, policyholderName, address, contact, vehicleMake, vehicleModel, vehicleYear, licensePlate, accidentDescription });
    res.status(201).json({ message: 'Insurance claim submitted successfully', data: newClaim });
  } catch (error) {
    res.status(500).json({ message: 'Error claiming insurance', error: error.message });
