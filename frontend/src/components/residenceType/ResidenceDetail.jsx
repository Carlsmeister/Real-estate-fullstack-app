import { useEffect, useState } from 'react';
import {Link, useParams} from 'react-router-dom';
import './ResidenceDetail.css';


function ResidenceDetail() {
    const { id } = useParams();
    const [residence, setResidence] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/v1/residence/${id}`)
            .then(response =>  {
                if (!response.ok) {
                    throw new Error('Residence not found');
                }
                return response.json()
            })
            .then(data => {
                console.log('API Response:', data);
                setResidence(data)
            })
            .catch(error => console.error('Error fetching residence:', error));
    }, [id]);

    if (!residence) {
        return <p>Loading...</p>;
    }

    return (
        <div className="container">
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
            <div className="residence-detail-container">
                <h2 className="title">{residence.address}, {residence.city}</h2>
                <p><strong>Type:</strong> {residence.residenceType}</p>
                <p><strong>Asking Price:</strong> {residence.askingPrice} SEK</p>
                <p><strong>Living Area:</strong> {residence.livingAreaSqm} sqm</p>
                <p><strong>Land Area:</strong> {residence.landAreaSqm} sqm</p>
                <p><strong>Price per sqm:</strong> {residence.sqmPriceSek} SEK</p>
                <p><strong>Number of Rooms:</strong> {residence.numberOfRooms}</p>
                <p><strong>Published Date:</strong> {residence.published}</p>
                <p><strong>Coordinates:</strong> {residence.coordinates}</p>
            </div>
        </div>
    );
}

export default ResidenceDetail;