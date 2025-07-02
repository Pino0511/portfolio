// Save a cookie with a given name and value
function setCookie(name, value, days = 365) {
    const expires = new Date(Date.now() + days * 86400000).toUTCString();
    document.cookie = `${name}=${encodeURIComponent(value)}; expires=${expires}; path=/`;
}

// Read a cookie by name
function getCookie(name) {
    const cookies = document.cookie.split(';').map(c => c.trim());
    const found = cookies.find(c => c.startsWith(name + "="));
    return found ? decodeURIComponent(found.split('=')[1]) : null;
}

// Get list of seen movie titles from cookies
function getSeen() {
    const raw = getCookie('seen');
    return raw ? JSON.parse(raw) : [];
}

// Check if a movie is marked as seen
function isSeen(title) {
    return getSeen().includes(title);
}

// Toggle the seen/unseen state of a movie
function toggleSeen(title) {
    let seen = getSeen();
    if (seen.includes(title)) {
        seen = seen.filter(t => t !== title);
    } else {
        seen.push(title);
    }
    setCookie('seen', JSON.stringify(seen));
    filterMovies(searchText, filterState);
}

// Application state
let allMovies = [];
let searchText = '';
let filterState = 'all';

// Get the genre from the URL
function getGenreFromURL() {
    const params = new URLSearchParams(window.location.search);
    return params.get('categoria') || 'comedy';
}

// Fetch movie data from external API
function fetchMovies(genre) {
    fetch(`https://api.sampleapis.com/movies/${genre}`)
        .then(res => res.ok ? res.json() : Promise.reject('Failed to fetch movies'))
        .then(data => {
            allMovies = data;
            filterMovies(searchText, filterState);
        })
        .catch(err => {
            console.error(err);
            document.getElementById('filmList').textContent = 'Error loading movies';
        });
}

// Filter and render the movie list
function filterMovies(text, state) {
    searchText = text.toLowerCase();
    filterState = state;

    const filtered = allMovies.filter(movie => {
        const title = (movie.title || '').toLowerCase();
        const matchText = title.includes(searchText);
        const seen = isSeen(movie.title);
        const matchSeen =
            state === 'all' ||
            (state === 'seen' && seen) ||
            (state === 'unseen' && !seen);
        return matchText && matchSeen;
    });

    renderMovies(filtered);
}

// Display movie cards in the UI
function renderMovies(movies) {
    const list = document.getElementById('filmList');
    list.innerHTML = '';
    movies.forEach(movie => {
        const li = document.createElement('li');
        li.className = 'movie-card';

        const title = movie.title;
        const image = movie.posterURL;
        const seen = isSeen(title);

        li.innerHTML = `
      ${image ? `<img src="${image}" alt="${title}" class="img-card">` : ''}
      <h3 style="${seen ? 'text-decoration: line-through; color: gray;' : ''}">${title}</h3>
      <button class="btn-seen" onclick="toggleSeen('${title.replace(/'/g, "\\'")}')">${seen ? '✅ Seen' : '⬜ To Watch'}</button>
      <button class="btn-remove" onclick="removeMovie('${title.replace(/'/g, "\\'")}')">🗑️ Remove</button>
    `;
        list.appendChild(li);
    });
}

// Remove a movie from the list
function removeMovie(title) {
    allMovies = allMovies.filter(movie => movie.title !== title);
    setCookie('seen', JSON.stringify(getSeen().filter(t => t !== title)));
    filterMovies(searchText, filterState);
}

// Sorting functions
function sortMoviesAsc() {
    const sorted = [...allMovies].sort((a, b) => a.title.localeCompare(b.title));
    renderMovies(sorted);
}

function sortMoviesDesc() {
    const sorted = [...allMovies].sort((a, b) => b.title.localeCompare(a.title));
    renderMovies(sorted);
}

// App setup
document.addEventListener('DOMContentLoaded', () => {
    const genre = getGenreFromURL();
    fetchMovies(genre);

    document.getElementById('searchInput').addEventListener('input', e => {
        filterMovies(e.target.value, filterState);
    });

    document.getElementById('filterStatus').addEventListener('change', e => {
        filterMovies(searchText, e.target.value);
    });

    document.getElementById('sortAscBtn').addEventListener('click', sortMoviesAsc);
    document.getElementById('sortDescBtn').addEventListener('click', sortMoviesDesc);

    document.getElementById('addFilmForm').addEventListener('submit', e => {
        e.preventDefault();
        const title = document.getElementById('filmTitle').value.trim();
        const image = document.getElementById('filmImage').value.trim();
        if (title) {
            allMovies.push({ title, posterURL: image });
            filterMovies(searchText, filterState);
        }
    });
});

// Observable: remove first seen movie every 2 minutes
setInterval(() => {
    let seen = getSeen();
    if (seen.length > 0) {
        seen.shift();
        setCookie('seen', JSON.stringify(seen));
        filterMovies(searchText, filterState);
    }
}, 120000);
