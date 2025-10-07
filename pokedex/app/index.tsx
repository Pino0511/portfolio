import { Image, ScrollView, StyleSheet, Text, View } from "react-native";

export default function Page() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Pokédex</Text>
      <View style={styles.imageContainer}>
        <Image
          source={{
            uri: "https://www.pokemonmillennium.net/wp-content/uploads/2021/04/pokedex-kanto-1.jpg",
          }}
          style={styles.image}
          resizeMode="cover"
        />
      </View>
      <Text style={styles.description}>
        The Pokédex is a personal digital assistant designed to catalog and provide information about the various species of Pokémon. Invented by Professor Oak, an expert in Pokémon studies, it collects data from many different sources.
        {"\n\n"}
        The device changes appearance with each generation, offering new features compatible with previous versions. The latest versions allow you to sort Pokémon by different criteria, such as national number, regional number, or alphabetical order.
      </Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 24,
    backgroundColor: "#f6f8fc",
    alignItems: "center",
    minHeight: "100%",
  },
  title: {
    fontSize: 44,
    fontWeight: "bold",
    color: "#e3350d",
    marginBottom: 18,
    textShadowColor: "#fff",
    textShadowOffset: { width: 1, height: 1 },
    textShadowRadius: 2,
    letterSpacing: 1,
  },
  imageContainer: {
    borderRadius: 18,
    overflow: "hidden",
    elevation: 4,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.18,
    shadowRadius: 6,
    marginBottom: 22,
    backgroundColor: "#fff",
  },
  image: {
    width: 340,
    height: 180,
  },
  description: {
    fontSize: 18,
    color: "#333",
    fontStyle: "italic",
    lineHeight: 26,
    textAlign: "justify",
    backgroundColor: "#fff",
    padding: 18,
    borderRadius: 14,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.07,
    shadowRadius: 3,
    marginTop: 8,
    marginBottom: 24,
  },
});