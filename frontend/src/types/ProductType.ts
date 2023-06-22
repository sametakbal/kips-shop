export interface ProductType {
  id: string;
  name: string;
  images: Array<string> | [];
  description: string;
  brand: string;
  category: string;
  price: number;
  countInStock: number;
  rating: number;
  numReviews: number;
}
