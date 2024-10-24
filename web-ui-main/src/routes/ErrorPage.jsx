import {useRouteError} from "react-router-dom";

export default function ErrorPage() {

    const error = useRouteError();
    console.log(error);
    return (
        <div>
            <h1>Upsii soooo sorrry</h1>
            <p>
                I guess you should look at your code again. Something is not adding up :(
            </p>
            <p>
                Here you see what you did wrong:
            </p>
            <p>
                <i>{error.statusText || error.message}</i>
            </p>

        </div>


    );


}