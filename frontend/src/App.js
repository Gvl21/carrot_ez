import { useState, useEffect, React } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Member from './pages/Member';
import Main from './pages/Main';

function App() {
    const [msg, setMsg] = useState('');
    const [newMember, setNewMember] = useState('');

    // const getNewMember = () => {
    //     fetch('/member')
    //         .then((res) => res.text())
    //         .then((txt) => setNewMember(txt));
    // };

    return (
        <div className='App'>
            <header className='App-header'>
                <Router>
                    <Routes>
                        <Route path='/' element={<Main />} />
                        <Route path='/member' element={<Member />} />
                    </Routes>
                </Router>
            </header>
        </div>
    );
}

export default App;
