export const addDecimals = (num) => {
    return (Math.round(num * 100) / 100).toFixed(2);
};

export const updateCart = (state) => {

    state.itemPrice = addDecimals(state.cartItems.reduce((acc, item) => acc + item.price * item.qty, 0));
    state.shippingPrice = addDecimals(state.itemPrice > 100 ? 0 : 100);
    state.taxPrice = addDecimals(Number((0.15 * state.itemPrice).toFixed(2)));
    state.totalPrice = (Number(state.itemPrice) + Number(state.shippingPrice) + Number(state.taxPrice)).toFixed(2);

    localStorage.setItem('cart', JSON.stringify(state));
}