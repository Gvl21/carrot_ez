import React from 'react';
import { Navigate, useNavigate } from 'react-router-dom';

function Main() {
    const navigate = useNavigate();
    const goNewMember = () => {
        navigate('/member');
    };
    return (
        <div>
            <button onClick={goNewMember}>회원가입 하기 </button>
        </div>
    );
}

export default Main;
