import { Image, Text, View } from "react-native";

interface Props {
  name: string,
  index: number
}

export default function ListItem({name, index}: Props) {
  return (
    <View style={{ flexDirection: 'row', alignItems: 'center', gap: 10, borderWidth: 1, borderColor: '#ccc', padding: 10, borderRadius: 20, margin: 10, backgroundColor: 'rgba(255,255,255,.8)'}}>
      <Image source={{
        uri: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + (index + 1) + ".png",
        width: 80,
        height: 80,
      }} />
      <Text style={{ fontSize: 24}}>{name}</Text>
    </View>
  )
}