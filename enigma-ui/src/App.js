import React, { useState } from 'react';
import axios from 'axios';
import './App.css'; 

function App() {
  const [text, setText] = useState('');
  const [config, setConfig] = useState(['A', 'A', 'A']);
  const [output, setOutput] = useState('');

  const handleConfigChange = (index, value) => {
    const newConfig = [...config];
    newConfig[index] = value.toUpperCase(); 
    setConfig(newConfig);
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/process', {
        message: text,
        rotorSettings: config.join(''), 
      });
      setOutput(response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const renderRotorOptions = () => {
    const alphabets = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
    return alphabets.map((letter, index) => (
      <option key={index} value={letter}>
        {letter}
      </option>
    ));
  };

  return (
    <div className="App">
      <h1>Enigma</h1>
      <div className="input-group">
        <label htmlFor="text">Message:</label>
        <input
          type="text"
          id="text"
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
      <div className="input-group">
        {[0, 1, 2].map((index) => (
          <div key={index}>
            <label htmlFor={`config${index}`}>Rotor {index+1}:</label>
            <select
              id={`config${index}`}
              value={config[index]}
              onChange={(e) => handleConfigChange(index, e.target.value)}
            >
              {renderRotorOptions()}
            </select>
          </div>
        ))}
      </div>
      <button onClick={handleSubmit}>Submit</button>
      <div className="output-group">
        <label htmlFor="output">Output:</label>
        <textarea id="output" value={output} readOnly />
      </div>
    </div>
  );
}

export default App;
