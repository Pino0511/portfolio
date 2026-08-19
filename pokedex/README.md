# Pokédex React Native App

This is a simple Pokédex app built with React Native and Expo.  
It allows you to browse a list of Pokémon, view their details, types, abilities, and moves, using data from the [PokéAPI](https://pokeapi.co/).

## Features

- Browse a paginated list of Pokémon
- View detailed information for each Pokémon, including:
  - Official artwork (normal and shiny)
  - Types
  - Abilities
  - Moves (first 10 shown)
- Modern and responsive UI
- Navigation with tabs and stack screens

## Screenshots

![Screenshot 1](pokedex.png)

## Getting Started

### Prerequisites

- [Node.js](https://nodejs.org/)
- [Expo CLI](https://docs.expo.dev/get-started/installation/)

### Installation

1. Clone the repository:

```sh
git clone <repository-url>
cd pokedex
```

2. Install dependencies:

```sh
npm install
```

3. Start the development server:

```sh
npx expo start
```

4. Scan the QR code with the Expo Go app or run on an emulator.

## Project Structure

- `app/` - Main application code
  - `index.tsx` - Home screen with Pokédex introduction
  - `_layout.tsx` - Tab navigation layout
  - `secondo-tab/` - Secondary tab with Pokémon list and details
    - `lista-pokemon.tsx` - Pokémon list screen
    - `dettaglio-pokemon.tsx` - Pokémon detail screen
- `types/` - TypeScript types for API responses
- `list-item.tsx` - List item component for Pokémon

## API

This app uses [PokéAPI](https://pokeapi.co/) for all Pokémon data.

## ❓ Troubleshooting

### Expo CLI installation issues
- Ensure Node.js is installed: `node --version`
- Install Expo CLI globally: `npm install -g expo-cli`
- On Windows, you may need to run PowerShell as Administrator
- Clear npm cache: `npm cache clean --force`

### Device emulator won't start
- For Android: Ensure Android Studio is installed and ANDROID_HOME environment variable is set
- For iOS: Requires macOS; ensure Xcode is installed via App Store
- Try resetting the Metro bundler: `npx expo start --clear`
- Verify emulator has sufficient storage and RAM (minimum 2GB free space recommended)

### QR code scanner not working
- Ensure Expo Go app is installed on your physical device
- Verify device is on same WiFi network as development machine
- Check that firewall allows local network connections
- Try using `npx expo start --tunnel` for tunnel connection mode

### API connection errors / PokéAPI unreachable
- Check internet connection
- Verify PokéAPI is operational at https://pokeapi.co/
- Add try-catch blocks around fetch calls
- Consider implementing retry logic with exponential backoff
- Check if requests are being blocked by CORS or firewall

### Node.js version conflicts
- Verify Node.js version compatibility: `node --version`
- Expo typically requires Node.js 14.x or higher
- Use nvm (Node Version Manager) to manage multiple Node versions
- Clear node_modules and reinstall: `rm -rf node_modules && npm install`

### App crashes on startup
- Check console logs: `npx expo start` shows detailed error messages
- Verify all dependencies are installed: `npm install`
- Clear cache: `npx expo start --clear`
- Check for missing environment variables or configuration files

### Performance issues / Slow rendering
- Optimize list rendering using FlatList with proper keyExtractor
- Reduce image sizes or use lower resolution artwork
- Implement proper memoization for components
- Profile app with React Native Debugger or Flipper
