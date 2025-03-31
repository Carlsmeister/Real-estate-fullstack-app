import { useEffect, useState } from 'react';
import { Link, useSearchParams } from 'react-router-dom';
import './ResidenceList.css';

function ResidenceList() {
    const [residences, setResidences] = useState([]);
    const [searchParams] = useSearchParams();

    useEffect(() => {
        const queryParams = new URLSearchParams();
        searchParams.forEach((value, key) => {
            if (value) queryParams.append(key, value);
        });

        const url = `http://localhost:8080/api/v1/residence?${queryParams.toString()}`;
        fetch(url)
            .then(response => {
                if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
                return response.json();
            })
            .then(data => setResidences(data))
            .catch(error => {
                console.error('Error fetching residences:', error);
                setResidences([]);
            });
    }, [searchParams]);

    return (
        <div className="residence-list-container">
            <nav className="navbar">
                <div className="navbar-inner">
                    <div className="logo">
                        <img src="/Logo-No-Slogan.png" alt="Swedish Residence Prices logo" className="logo-image" />
                    </div>
                    <ul className="nav-links">
                        <li>
                            <Link to="/" className="nav-link">
                                <i className="fas fa-home"></i> Home
                            </Link>
                        </li>
                        <li>
                            <Link to="/search" className="nav-link">
                                <i className="fas fa-search"></i> Search
                            </Link>
                        </li>
                        <li>
                            <Link to="/contact" className="nav-link">
                                <i className="fas fa-envelope"></i> Contact
                            </Link>
                        </li>
                    </ul>
                </div>
            </nav>
            <h2>Residences</h2>
            {residences.length === 0 ? (
                <p>No residences found.</p>
            ) : (
                <ul>
                    {residences.map(residence => (
                        <li key={residence.residenceAddressId}>
                            <Link to={`/residence/${residence.residenceAddressId}`}>
                                {residence.address} - {residence.city} - {residence.askingPrice} SEK
                            </Link>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default ResidenceList;