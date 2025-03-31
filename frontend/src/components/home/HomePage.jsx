import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './HomePage.css'; // Import the CSS file

function HomePage() {
    const [searchQuery, setSearchQuery] = useState('');
    const [cityFilter] = useState('');
    const [typeFilter, setTypeFilter] = useState('');
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const [minRooms, setMinRooms] = useState('');
    const [maxRooms, setMaxRooms] = useState('');
    const [minLivingArea, setMinLivingArea] = useState('');
    const [maxLivingArea, setMaxLivingArea] = useState('');
    const [minLandArea, setMinLandArea] = useState('');
    const [maxLandArea, setMaxLandArea] = useState('');

    const navigate = useNavigate();

    const residenceTypes = [
        'HOUSE', 'APARTMENT', 'VACATION_HOUSE', 'WINTERIZED_VACATION_HOME',
        'TWIN/PAIR_HOUSE', 'ROW_HOUSE', 'FORESTING_ESTATE', 'PLOT',
        'AGRICULTURAL_ESTATE', 'HOMESTEAD', 'ALL'
    ];

    const buildQueryParams = () => {
        const queryParams = new URLSearchParams();
        if (searchQuery) queryParams.append('address', searchQuery);
        if (cityFilter) queryParams.append('city', cityFilter);
        if (typeFilter && typeFilter !== 'ALL') queryParams.append('type', typeFilter);
        if (minPrice) queryParams.append('minPrice', minPrice);
        if (maxPrice) queryParams.append('maxPrice', maxPrice);
        if (minRooms) queryParams.append('minRooms', minRooms);
        if (maxRooms) queryParams.append('maxRooms', maxRooms);
        if (minLivingArea) queryParams.append('minLivingArea', minLivingArea);
        if (maxLivingArea) queryParams.append('maxLivingArea', maxLivingArea);
        if (minLandArea) queryParams.append('minLandArea', minLandArea);
        if (maxLandArea) queryParams.append('maxLandArea', maxLandArea);
        return queryParams.toString();
    };

    const handleSearch = () => {
        const queryString = buildQueryParams();
        navigate(`/residences?${queryString}`);
    };

    const handleCardClick = (type) => {
        setTypeFilter(type);
        const queryString = buildQueryParams();
        navigate(`/residences?type=${type}${queryString ? '&' + queryString : ''}`);
    };

    return (
        <div className="homepage-container">
            {/* Navbar */}
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

            {/* Welcome Section */}
            <section className="welcome-section">
                <h1 className="welcome-title">Welcome to Swedish Residence Prices</h1>
                <p className="welcome-text">
                    Explore real-time real estate prices for active sales across Sweden.
                    Find your dream home with up-to-date listings and detailed insights.
                </p>
                <div className="search-container">
                    <input
                        className="search-bar"
                        type="text"
                        placeholder="Search for an address, area..."
                        value={searchQuery}
                        onChange={e => setSearchQuery(e.target.value)}
                        onKeyPress={e => e.key === 'Enter' && handleSearch()}
                    />
                    <button className="search-button" onClick={handleSearch}>Search</button>
                </div>
            </section>

            {/* Filters and Cards Section */}
            <section className="filters-and-cards">
                <div className="filters-container">
                    <h3>Filters</h3>
                    <input
                        type="number"
                        placeholder="Min Price (SEK)"
                        value={minPrice}
                        onChange={e => setMinPrice(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max Price (SEK)"
                        value={maxPrice}
                        onChange={e => setMaxPrice(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Min Rooms"
                        value={minRooms}
                        onChange={e => setMinRooms(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max Rooms"
                        value={maxRooms}
                        onChange={e => setMaxRooms(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Min Living Area (sqm)"
                        value={minLivingArea}
                        onChange={e => setMinLivingArea(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max Living Area (sqm)"
                        value={maxLivingArea}
                        onChange={e => setMaxLivingArea(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Min Land Area (sqm)"
                        value={minLandArea}
                        onChange={e => setMinLandArea(e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max Land Area (sqm)"
                        value={maxLandArea}
                        onChange={e => setMaxLandArea(e.target.value)}
                    />
                    <button className="filter-button" onClick={handleSearch}>Apply Filters</button>
                </div>
                <div className="cards-section">
                    <h3>Choose Type</h3>
                    <div className="cards-grid">
                        {residenceTypes.map((type) => (
                            <div
                                key={type}
                                className={`card ${typeFilter === type ? 'active' : ''}`}
                                onClick={() => handleCardClick(type)}
                            >
                                <h3 className="card-title">{type.replace('_', ' ')}</h3>
                            </div>
                        ))}
                    </div>
                </div>
            </section>
        </div>
    );
}

export default HomePage;