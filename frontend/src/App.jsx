import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './components/Home/HomePage.jsx';
import ResidenceList from './components/list/ResidenceList.jsx';
import ResidenceDetail from './components/residenceType/ResidenceDetail';

function App() {
    return (
        <Router>
            <div className="app">
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/residences" element={<ResidenceList />} />
                    <Route path="/residence/:id" element={<ResidenceDetail />} />
                    {/* Add /search and /contact routes later */}
                </Routes>
            </div>
        </Router>
    );
}

export default App;