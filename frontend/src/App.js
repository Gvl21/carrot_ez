import { useState, useEffect, React } from 'react';

function App() {
    const [msg, setMsg] = useState(' ');

    useEffect(() => {
        fetch('/hello')
            .then((res) => res.text())
            .then((txt) => setMsg(txt));
    });

    return (
        <div className='App'>
            <header className='App-header'>
                <p>{msg}</p>
                <p>짜증</p>
            </header>
        </div>
    );
}

export default App;
