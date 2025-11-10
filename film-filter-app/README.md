# 🎬 Film Filter App

A simple, responsive web app to browse, filter, and manage a list of movies. Ideal as a static frontend project demonstrating the use of fetch, DOM manipulation, and client-side state handling.

## ✨ Main features

- ✅ Fetch movies by genre from a public API
- 🔍 Search movies by title (live filter)
- 👁️ Mark movies as "seen" / "unseen" (state saved via cookies)
- 🗑️ Remove movies from the list
- ➕ Add custom (temporary) movies
- 🔃 Sort the list A→Z and Z→A
- 💻 Responsive layout for desktop, tablet and mobile
- 🕒 Demonstration feature: automatically removes the first "seen" movie (configurable in the code)

## 📷 Screenshot

![Screenshot dell'app](./film-app.png)

## 📦 Stack and dependencies

- HTML5
- CSS3
- Vanilla JavaScript (ES6+)
- Fetch API for HTTP requests
- Cookies API for state persistence

## 🚀 How to run

1. Clone the repository or download the files
2. Open `index.html` in your web browser (or use a local server)
3. The app loads the initial list of movies from the API
4. Start filtering and managing movies!

### Optional: Run with a local server

For better development experience, use a local server:

```bash
# Using Python 3
python -m http.server 8000

# Using Node.js with http-server
npx http-server

# Using Live Server in VS Code
# Install the "Live Server" extension and right-click index.html > "Open with Live Server"
```

Then navigate to `http://localhost:8000` (or the appropriate port).

## 📚 File structure

- `index.html` - Main HTML file with the DOM structure
- `css/` - Stylesheets (responsive design)
- `js/` - JavaScript modules (fetch logic, DOM manipulation, state management)
- `film-app.png` - Screenshot for README

## ❓ Troubleshooting

### API call failures / Movies not loading
- Check browser console for network errors: press `F12` → Console tab
- Verify the movie database API is accessible and online
- Check if the API endpoint URL is correct in your JavaScript code
- Look for CORS (Cross-Origin Resource Sharing) errors in the console
- Try accessing the API directly in the browser address bar to verify it works
- Ensure your internet connection is active

### CORS errors (Cross-Origin Resource Sharing)
- Error message: `Access to XMLHttpRequest at 'https://api.example.com' from origin 'null' has been blocked by CORS policy`
- This typically occurs when running locally with `file://` protocol
- Solution: Run the app using a local server instead (see "Run with a local server" section)
- Alternatively, check if the API supports CORS or use a CORS proxy (be cautious with proxies)

### Movies not saving or persisting after refresh
- Check browser cookie settings: verify cookies are enabled
- Open DevTools: `F12` → Application tab → Cookies → Check if your domain has stored cookies
- Clear cookies and localStorage if corrupted: `F12` → Application → Storage → Clear Site Data
- Check browser's private/incognito mode - cookies don't persist in private browsing
- Verify the cookie setting code is correct and running without errors

### Search/Filter not working
- Open browser console (`F12`) and check for JavaScript errors
- Verify the search input element has correct ID or class in JavaScript
- Check that event listeners are properly attached to the input field
- Test with different keywords to ensure the filter logic works
- Check console for `TypeError: Cannot read property of undefined` errors

### Sorting not working or behaving unexpectedly
- Clear browser cache and reload: `Ctrl+Shift+Delete` (or `Cmd+Shift+Delete` on Mac)
- Check that the sort function handles special characters correctly
- Verify case-sensitivity in sort comparison (use `.toLowerCase()` if needed)
- Ensure the DOM is being properly updated after sorting
- Test sorting with different movie lists to isolate the issue

### Layout looks broken / Not responsive on mobile
- Clear browser cache: `F12` → Network → disable cache, then reload
- Check viewport meta tag exists in HTML: `<meta name="viewport" content="width=device-width, initial-scale=1.0">`
- Verify CSS media queries are correct
- Test in different browsers to ensure cross-browser compatibility
- Resize browser window to test responsiveness (or use DevTools Device Mode: `F12` → Toggle Device Toolbar)

### Cookies not working / "Add custom movie" feature fails
- Verify cookies are enabled in browser settings
- Check browser console for any cookie-related errors
- Try clearing all site data: `F12` → Application → Clear Site Data
- Test in a different browser to isolate browser-specific issues
- Ensure cookie name doesn't conflict with other sites

### Browser compatibility issues
- Test in different browsers (Chrome, Firefox, Safari, Edge)
- Check console for unsupported JavaScript features
- Some older browsers don't support ES6 features like arrow functions or template literals
- Consider using a transpiler like Babel if targeting older browsers
- Verify CSS prefixes for older browser support (e.g., `-webkit-`, `-moz-`)

### Performance issues / App runs slowly
- Open DevTools Performance tab: `F12` → Performance
- Record a session and identify bottlenecks
- Check for excessive DOM updates or inefficient loops
- Optimize API calls - avoid fetching duplicate data
- Consider implementing pagination or lazy loading for large movie lists
- Minify CSS and JavaScript for production
