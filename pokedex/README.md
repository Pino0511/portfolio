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

