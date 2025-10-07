import { Link, useRouter } from "expo-router";
import { useEffect, useState } from "react";
import { Button, FlatList, Image, ImageBackground, ListRenderItemInfo, Text, TouchableOpacity, View } from "react-native";
import { PokedexResponse, Pokemon } from "../../types";
import ListItem from "../../list-item";

export default function PokemonList() {

  // const url = "https://pokeapi.co/api/v2/pokemon";
  const [ url, setUrl ] = useState<string>("https://pokeapi.co/api/v2/pokemon");

  const [ pokedex, setPokedex ] = useState<PokedexResponse>();

  function getPokedex() {
    fetch(url)
      .then(response => response.json())
      .then((value: PokedexResponse) => {
        setUrl(value.next);
        setPokedex(value)
        // setPokedex({
        //   ...pokedex,
        //   results: [
        //     ...pokedex?.results,
        //     ...value.results
        //   ]
        // })
      })
  }

  function loadMore(url: string) {
    fetch(url)
      .then(response => response.json())
      .then((value: PokedexResponse) => {
        setUrl(value.next);
        if (pokedex) {
          setPokedex({
            ...pokedex,
            results: [...pokedex.results, ...value.results]
          })
        }
      })
  }

  useEffect(() => {
    // fetch...
    getPokedex();
  }, []);

  const router = useRouter();

  function renderItem(info: ListRenderItemInfo<Pokemon>) {
    return (
      <TouchableOpacity onPress={() => router.push({
        pathname: '/secondo-tab/dettaglio-pokemon',
        params: {
          name: info.item.name,
          url: info.item.url,
        }
      })}>
        <ListItem name={info.item.name} index={info.index} />
      </TouchableOpacity>
    )
  }

  return (
<ImageBackground source={{ uri: 'https://i.pinimg.com/564x/1a/28/b6/1a28b693431f1f5316d80b02789a9da6.jpg'}} >
    <FlatList
      data={pokedex?.results}
      renderItem={renderItem}
      keyExtractor={item => item.name}
      // ListFooterComponent={() => <Button onPress={() => loadMore(url)} title="Load more" />}
      onEndReached={() => loadMore(url)}
    />
    </ImageBackground>

    // <View>
    //   <Text>Pokémon list</Text>
    //   <Link href={'/secondo-tab/dettaglio-pokemon'}>Pokémon details</Link>
    //   <Link href={'./dettaglio-pokemon'}>Pokémon details</Link>
    // </View>
  )
}