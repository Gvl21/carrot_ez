import { useState, useEffect, React } from 'react';

function App() {
    const [msg, setMsg] = useState('');
    const [member, setMember] = useState('');

    const getMember = () => {
        fetch('/member')
            .then((res) => res.text())
            .then((txt) => setMember(txt));
    };

    useEffect(() => {
        fetch('/hello')
            .then((res) => res.text())
            .then((txt) => setMsg(txt));
    });

    return (
        <div className='App'>
            <header className='App-header'>
                <p>{msg}</p>
                <p>{member}</p>
                <p>어서오세요</p>
                <button onClick={getMember}>멤버 컨트롤러 api 테스트 </button>
            </header>
        </div>
    );
}

export default App;
