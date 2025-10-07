import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";
import { Image, ScrollView, StyleSheet, Text, View, ActivityIndicator } from "react-native";
import { DettaglioPokemonResponse } from "../../types";

type PokemonDetailType = {
  name: string,
  url: string
};

function capitalize(str?: string) {
  if (!str) return "";
  return str.charAt(0).toUpperCase() + str.slice(1);
}

function Group({ title, list }: { title: string; list: string[] }) {
  return (
    <View style={styles.group}>
      <Text style={styles.groupTitle}>{title}</Text>
      <View style={styles.groupList}>
        {list.length === 0 ? (
          <Text style={styles.groupEmpty}>No data</Text>
        ) : (
          list.map((item, idx) => (
            <Text key={item + idx} style={styles.groupItem}>
              {capitalize(item)}
            </Text>
          ))
        )}
      </View>
    </View>
  );
}

export default function PokemonDetail() {
  const { name, url } = useLocalSearchParams<PokemonDetailType>();
  const [detail, setDetail] = useState<DettaglioPokemonResponse>();

  useEffect(() => {
    fetch(url)
      .then((response) => response.json())
      .then((value) => setDetail(value));
  }, []);

  if (!detail) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#e3350d" />
        <Text style={{ color: "#e3350d", marginTop: 12 }}>Loading...</Text>
      </View>
    );
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>{capitalize(name)}</Text>
        <View style={styles.imagesRow}>
          <Image
            source={{
              uri: detail.sprites.other["official-artwork"].front_default,
            }}
            style={styles.pokemonImage}
          />
          <Image
            source={{
              uri: detail.sprites.other["official-artwork"].front_shiny,
            }}
            style={styles.pokemonImage}
          />
        </View>
        <Group title="Types" list={detail.types.map((item) => item.type.name)} />
        <Group title="Abilities" list={detail.abilities.map((item) => item.ability.name)} />
        <Group title="Moves" list={detail.moves.slice(0, 10).map((item) => item.move.name)} />
        {/* Shows only the first 10 moves to avoid a too long list */}
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 24,
    backgroundColor: "#f6f8fc",
    alignItems: "center",
  },
  loadingContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#f6f8fc",
    height: "100%",
  },
  card: {
    backgroundColor: "#fff",
    borderRadius: 18,
    padding: 24,
    width: "100%",
    maxWidth: 420,
    alignItems: "center",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.10,
    shadowRadius: 8,
    elevation: 4,
  },
  title: {
    fontSize: 36,
    fontWeight: "bold",
    color: "#e3350d",
    marginBottom: 18,
    letterSpacing: 1,
    textAlign: "center",
  },
  imagesRow: {
    flexDirection: "row",
    justifyContent: "center",
    marginBottom: 18,
    gap: 16,
  },
  pokemonImage: {
    width: 130,
    height: 130,
    marginHorizontal: 6,
    borderRadius: 12,
    backgroundColor: "#f6f8fc",
    borderWidth: 1,
    borderColor: "#eee",
  },
  group: {
    width: "100%",
    marginBottom: 18,
    alignItems: "flex-start",
  },
  groupTitle: {
    fontSize: 20,
    fontWeight: "bold",
    color: "#e3350d",
    marginBottom: 6,
    marginLeft: 4,
  },
  groupList: {
    flexDirection: "row",
    flexWrap: "wrap",
    gap: 8,
    marginLeft: 4,
  },
  groupItem: {
    backgroundColor: "#f6f8fc",
    color: "#333",
    fontSize: 16,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 8,
    marginRight: 6,
    marginBottom: 6,
    borderWidth: 1,
    borderColor: "#e3350d22",
  },
  groupEmpty: {
    color: "#888",
    fontStyle: "italic",
    fontSize: 16,
  },
});