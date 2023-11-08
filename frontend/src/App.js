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
                <p>어서오오</p>
                <p>할 수 있어요!</p>
                <p>우리는 할 수 있어! </p>
                <p>!!!!!!!!! </p>
            </header>
        </div>
    );
}

export default App;
