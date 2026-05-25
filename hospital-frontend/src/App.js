import React, { useEffect, useState } from "react";

function App() {

  const [patients, setPatients] = useState([]);

  const [formData, setFormData] = useState({
    name: "",
    age: "",
    gender: "",
    bloodPressure: "",
    heartRate: "",
    temperature: ""
  });

  // FETCH ALL PATIENTS
  const fetchPatients = async () => {
    try {
      const response = await fetch(
        "https://hospital-management-xths.onrender.com/patients"
      );

      const data = await response.json();

      setPatients(data);

    } catch (error) {
      console.error("Error fetching patients:", error);
    }
  };

  // LOAD PATIENTS ON PAGE LOAD
  useEffect(() => {
    fetchPatients();
  }, []);

  // HANDLE INPUT CHANGES
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // SUBMIT FORM
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      const response = await fetch(
        "https://hospital-management-xths.onrender.com/patients",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(formData)
        }
      );

      if (response.ok) {

        alert("Patient Added Successfully");

        setFormData({
          name: "",
          age: "",
          gender: "",
          bloodPressure: "",
          heartRate: "",
          temperature: ""
        });

        fetchPatients();

      } else {
        alert("Failed to add patient");
      }

    } catch (error) {
      console.error(error);
    }
  };

  return (

    <div style={{ padding: "30px", fontFamily: "Arial" }}>

      <h1>Hospital Management System</h1>

      <h2>Admit Patient</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleChange}
        />

        <br /><br />

        <input
          type="number"
          name="age"
          placeholder="Age"
          value={formData.age}
          onChange={handleChange}
        />

        <br /><br />

        <input
          type="text"
          name="gender"
          placeholder="Gender"
          value={formData.gender}
          onChange={handleChange}
        />

        <br /><br />

        <input
          type="text"
          name="bloodPressure"
          placeholder="Blood Pressure"
          value={formData.bloodPressure}
          onChange={handleChange}
        />

        <br /><br />

        <input
          type="number"
          name="heartRate"
          placeholder="Heart Rate"
          value={formData.heartRate}
          onChange={handleChange}
        />

        <br /><br />

        <input
          type="number"
          step="0.1"
          name="temperature"
          placeholder="Temperature"
          value={formData.temperature}
          onChange={handleChange}
        />

        <br /><br />

        <button type="submit">
          Admit Patient
        </button>

      </form>

      <hr />

      <h2>Patients List</h2>

      {patients.length === 0 ? (
        <p>No patients found</p>
      ) : (
        <ul>
          {patients.map((patient) => (
            <li key={patient.id}>
              {patient.name} | Age: {patient.age} | Gender: {patient.gender}
            </li>
          ))}
        </ul>
      )}

    </div>
  );
}

export default App;