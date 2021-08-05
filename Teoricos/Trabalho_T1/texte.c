for (int i = 0; i < (n - 1); i++) {
    int menor = i;

    for (int j = (i + 1); j < n; j++){
        if (array[menor] > array[j]){
    menor = j;
    }
}
    if (menor != i){
        swap(menor, i);
    }
}