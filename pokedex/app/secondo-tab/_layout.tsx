import { Stack } from "expo-router";

export default function StackLayout() {
  return (
    <Stack>
      <Stack.Screen name="lista-pokemon" options={{ title: "Pokémon List" }} />
      <Stack.Screen name="dettaglio-pokemon" options={{ title: "Pokémon Details" }} />
    </Stack>
  )
}