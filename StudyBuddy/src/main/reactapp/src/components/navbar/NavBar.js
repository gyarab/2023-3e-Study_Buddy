import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import {CssBaseline, Fab, Fade, Tab, Tabs, useMediaQuery, useScrollTrigger, useTheme} from "@mui/material";
import PropTypes from "prop-types";
import DrawerSB from "./drawer_sb/DrawerSB";

function ScrollTop(props) {
    const { children, window } = props;
    // Note that you normally won't need to set the window ref as useScrollTrigger
    // will default to window.
    // This is only being set here because the demo is in an iframe.
    const trigger = useScrollTrigger({
        target: window ? window() : undefined,
        disableHysteresis: true,
        threshold: 100,
    });

    const handleClick = (event) => {
        const anchor = (event.target.ownerDocument || document).querySelector(
            '#back-to-top-anchor',
        );

        if (anchor) {
            anchor.scrollIntoView({
                block: 'center',
            });
        }
    };

    return (
        <Fade in={trigger}>
            <Box
                onClick={handleClick}
                role="presentation"
                sx={{ position: 'fixed', bottom: 16, right: 16 }}
            >
                {children}
            </Box>
        </Fade>
    );


}

export default function BackToTop(props) {
    const theme = useTheme();
    const isMatch =useMediaQuery(theme.breakpoints.down('md'));
    const PAGESROUT = ["/", "/user/articles", "/user/article/create", "/user/account"];
    const PAGES = ["Home", "Articles", "Create Article", "Account"];

    return (
        <React.Fragment>
            <CssBaseline />
            <AppBar sx={{ background: "#211970"}}>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{marginRight: "15px"}}>
                        <a className="nav-link" href="/"> Study Buddy </a>
                    </Typography>

                    {isMatch ? (<>

                        <DrawerSB/>
                    </>):(<>
                        <Tabs textColor="inherit">
                            {PAGES.map((page, index) => (<a className="nav-link" href={PAGESROUT[index]}><Tab key={index} label={page} /></a>))}

                        </Tabs>

                        <Button sx={{ marginLeft: "auto"}   } variant="contained"><a className="nav-link" href="/login">Log-in</a></Button>
                        <Button sx={{ marginLeft: '10px'}} variant="contained"><a className="nav-link" href="/register">Sign-up</a></Button>
                    </>)
                    }
                </Toolbar>
            </AppBar>
            <Toolbar id="back-to-top-anchor" />
        </React.Fragment>
    );
}

ScrollTop.propTypes = {
    children: PropTypes.element.isRequired,
    /**
     * Injected by the documentation to work in an iframe.
     * You won't need it on your project.
     */
    window: PropTypes.func,
};